<template>
    <el-form class="product-form" ref="form" :model="formFields" size='small'>
        <el-form-item v-for="(item, name, index) in formFields" :key="index" > 
            <el-input v-model="formFields[name]" autosize :disabled="name == 'id' || name == 'storehouses_balance'">
                <template slot="prepend">{{ name }}</template>
            </el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">Save</el-button>
            <el-button disabled type="danger" @click="onDelete">Delete</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name: "ProductForm",
    props: ['compArgs'],
    data() {
        return {
            formFields: {
                id: '',
                name: '',
                sku: '',
                supplier_id: '',
                category_id: '',
                storehouses_balance: '',
            }
        }
    },
    methods: {
        onSubmit(){
            this.$axios
            .post('stock/product/create/', this.formFields)
            .then(response => {
                console.log(response)
                })
            .catch(error => {
                    console.log(error)
                })
            
        },
        onDelete(id){
            console.log("deleted", id)
        },

        loadData(args){
            // TODO Rewrite this func. It should rely on routing
            try {
                let id = args[0]['id']
                this.$axios
                .get(`stock/product/${id}/`)
                .then(response => {
            
                    let data = response.data.product_list
                    this.formFields.id = data.id
                    this.formFields.name = data.name
                    this.formFields.sku = data.sku
                    this.formFields.supplier_id = data.supplier_id
                    this.formFields.category_id = data.category_id

                })
                .catch(error => {
                    console.log(error)
                })
            } 
            catch (err){
                if (err.name == "TypeError") {
                    console.log("Create mode")
                }
            }
            
        }
    },
    created() {
        this.loadData(this.compArgs)
    },

}
</script>

<style scoped>
.product-form {
    width:45%; 
    margin-left:25%
}
</style>