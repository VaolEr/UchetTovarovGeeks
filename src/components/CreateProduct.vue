<template>
    <el-form class="product-form" ref="form" :model="formFields" size='small'>
        <el-form-item v-for="(item, name, index) in formFields" :key="index" > 
            <el-input v-model="formFields[name]" autosize :disabled="name == 'id'">
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
    name: "CreateProduct",
    data() {
        return {
            formFields: {
                id: '',
                name: '',
                sku: '',
                supplier: '',
                category: '',
                item_storehouses: '',
            }
        }
    },
    methods: {
        onSubmit(){
            this.$axios
            .post('stock/create/', this.formFields)
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
    }

}
</script>

<style scoped>
.product-form {
    width:45%; 
    margin-left:25%
}
</style>