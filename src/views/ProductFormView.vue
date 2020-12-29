<template>
    <el-form class="product-form" ref="form" :model="formFields" label-position="top" size='small'>
        <el-form-item > 
            <el-input v-model="formFields.id" autosize disabled>
                <template slot="prepend">id</template>
            </el-input>
        </el-form-item>
        <el-form-item > 
            <el-input v-model="formFields.name" autosize >
                <template slot="prepend">name</template>
            </el-input>
        </el-form-item>
        <el-form-item > 
            <el-input v-model="formFields.sku" autosize >
                <template slot="prepend">sku</template>
            </el-input>
        </el-form-item>
        <el-form-item > 
            <el-input v-model="formFields.supplier" autosize >
                <template slot="prepend">supplier</template>
            </el-input>
        </el-form-item>
        <el-form-item label = "Please select category" >
            <product-form-category v-bind:selected-categories.sync="selectedCategoryNames" @data-exchange="recieveUserSelections"/>
        </el-form-item> 
        <el-form-item>
            <el-button type="primary" @click="onSubmit">Save</el-button>
            <el-button disabled type="danger" @click="onDelete">Delete</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import ProductFormCategory from '../components/ProductFormCategory.vue'

export default {
  components: { ProductFormCategory },
    name: "ProductForm",
    data() {
        return {
            formFields: {
                id: '',
                name: '',
                sku: '',
                supplier: '',
                categories: [],
                storehouses_balance: [],
            }
        }
    },
    computed: {
      selectedCategoryNames() {
        let categoryNames = []
        this.formFields.categories.forEach(item => categoryNames.push(item.name))
        return categoryNames
        },
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
        loadData(){
            // TODO Rewrite this func. It should rely on routing
            try {
                let id = this.$route.params.id.toString()
                console.log('we passed ID!!!', typeof id)
                this.$axios
                .get(`stock/product/${id}/`)
                .then(response => {
            
                    let data = response.data.response_data
                    this.formFields.id = data.id
                    this.formFields.name = data.name
                    this.formFields.sku = data.sku
                    this.formFields.supplier_id = data.supplier_id
                    this.formFields.categories = data.categories

                })
                .catch(error => {
                    console.log('loadData error: ', error)
                })
            } 
            catch (err){
                if (err.name == "TypeError") {
                    console.log("Create mode")
                }
                else (console.log(err))
            }
            
        },
        recieveUserSelections(key, value){
          this.formFields[key]=value
        }
    },
    created() {
        this.loadData()
    },

}
</script>

<style scoped>
.product-form {
    width:45%; 
    margin-left:25%
}
</style>