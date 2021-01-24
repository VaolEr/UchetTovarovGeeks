<template>
    <el-form class="product-form" ref="form" :model="formFields" label-position="top" size='small'>
        <el-form-item > 
            <el-input v-model="formFields.id"  disabled>
                <template slot="prepend">id</template>
            </el-input>
        </el-form-item>
        <el-form-item > 
            <el-input v-model="formFields.name"  >
                <template slot="prepend">name</template>
            </el-input>
        </el-form-item>
        <el-form-item > 
            <el-input v-model="formFields.sku"  >
                <template slot="prepend">sku</template>
            </el-input>
        </el-form-item>
        
            <product-form-supplier v-bind:selected-supplier.sync="formFields.supplier" />
            <product-form-category v-bind:selected-categories.sync="selectedCategoryNames" @data-exchange="recieveUserSelections"/>
            <product-from-stock v-if="dataLoaded" v-bind:product_id="formFields.id" v-bind:storehouses-balance.sync="formFields.storehouses_balance" />
        
        <el-form-item>
            <el-button v-if="!formFields.id" type="success" @click="onSave">Save</el-button>
            <el-button v-if="formFields.id" type="primary" @click="onUpdate(formFields.id)">Update</el-button>
            <el-button v-if="formFields.id" type="danger" @click="onDelete(formFields.id)">Delete</el-button>
        </el-form-item>
    </el-form>
</template>


<script>
import ProductFormCategory from '../components/ProductFormCategory.vue'
import ProductFormSupplier from '../components/ProductFormSupplier.vue'
import ProductFromStock from '../components/ProductFormStock.vue'


export default {
  components: { ProductFormCategory, ProductFormSupplier, ProductFromStock },
    name: "ProductForm",
    data() {
        return {
            dataLoaded: false,
            formFields: {
                id: null,
                name: null,
                sku: null,
                supplier: null,
                unit: {id: 1000}, // TODO add unit ui + control
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
        
        onSave(){
            this.$axios
            .post('stock/product/create/', this.formFields)
            .then(response => {
                console.log(response.status)
                if (response.status === 201){
                    this.$router.push({ name: 'ProductFormViewEdit', params: { id: `${response.data.data.id}` } })
                }

            })
            .catch(error => {
                    console.log(error)
            })
            
        },
        onUpdate(id){
            this.$axios
            .put(`stock/product/${id}/`, this.formFields)
            .then(response => {
                console.log(response)
                })
            .catch(error => {
                    console.log(error)
                })
        },
        onDelete(id){
            this.$axios
                .delete(`stock/product/${id}/`)
                .then(response => {
                    console.log("successfully deleted. Server responded: ", response)
                    this.$router.push({name: 'ProductFormView'})
                    //DRY? Единственное различие в методе delete тут и в productList - тут редирект, там удаление строки из списка
                    //this.products.splice(this.products.indexOf(item => item.id === id),1)
                    //
                    })
                .catch(error => {
                    console.log(error)
                    this.request_error = true
                })
        },
        loadData(){
            try {
                let id = this.$route.params.id.toString()
                this.$axios
                .get(`stock/product/${id}/`)
                .then(response => {
            
                    let data = response.data.data
                    this.formFields.id = data.id
                    this.formFields.name = data.name
                    this.formFields.sku = data.sku
                    this.formFields.supplier = data.supplier
                    this.formFields.categories = data.categories
                    this.formFields.storehouses_balance = data.storehouses_balance
                    this.dataLoaded = true

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
        },
        receiveStorehousesBalance(storehouse_id, quantity){
            console.log(storehouse_id, quantity)
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