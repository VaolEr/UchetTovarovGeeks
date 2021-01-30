<template>
    <el-form class="product-form" ref="form" :model="formFields" label-position="top" size='small' v-loading.lock="!dataLoaded" >
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
        
            <product-form-supplier v-if="dataLoaded" v-bind:selected-supplier.sync="formFields.supplier" />
            <product-form-category v-if="dataLoaded" v-bind:selected-categories.sync="formFields.categories" />
            <product-form-unit v-if="dataLoaded" v-bind:selected-unit.sync="formFields.unit" />
            <product-from-stock v-if="dataLoaded" v-bind:product_id="formFields.id" v-bind:storehouses-balance.sync="formFields.storehouses_balance" />
        
        <el-form-item v-if="$store.getters.userRole === 'ADMIN' ">
            <el-button v-if="!formFields.id" type="success" @click="onSave" :loading="buttonLoading">Save</el-button>
            <el-button v-if="formFields.id" type="primary" @click="onUpdate(formFields.id)" :loading="buttonLoading">Update</el-button>
            <el-button v-if="formFields.id" type="danger" @click="onDelete(formFields.id)">Delete</el-button>
        </el-form-item>
    </el-form>
</template>


<script>
import ProductFormCategory from '../components/ProductFormCategory.vue'
import ProductFormSupplier from '../components/ProductFormSupplier.vue'
import ProductFromStock from '../components/ProductFormStock.vue'
import ProductFormUnit from '../components/ProductFormUnit.vue'


export default {
  components: { ProductFormCategory, ProductFormSupplier, ProductFromStock, ProductFormUnit },
    name: "ProductForm",
    data() {
        return {
            dataLoaded: false,
            buttonLoading: false,
            formFields: {
                id: null,
                name: null,
                sku: null,
                supplier: null,
                unit: null,
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
            this.buttonLoading = true
            this.$axios
            .post('stock/product/create/', this.formFields)
            .then(response => {
                console.log(response.status)
                if (response.status === 201){
                    this.$notify.success(`Item created`);
                    this.$router.push({ name: 'ProductFormViewEdit', params: { id: `${response.data.data.id}` } })
                }

            })
            .catch(error => {
                this.$notify.error(`Ошибка. ${error}`);
                console.log(error)
            this.buttonLoading = false
            })
            
        },
        onUpdate(id){
            this.buttonLoading = true
            this.$axios
            .put(`stock/product/${id}/`, this.formFields)
            .then(() => {
                this.$notify.success(`Item updated`);
                this.buttonLoading = false
                })
            .catch(error => {
                this.$notify.error(`ERROR`);
                console.log(error)
                })
            
        },
        onDelete(id){
            this.buttonLoading = true
            this.$axios
                .delete(`stock/product/${id}/`)
                .then(() => {
                    this.$notify.success(`Item deleted`);
                    this.$router.push({name: 'ProductFormView'})
                    })
                .catch(error => {
                    this.$notify.error(`ERROR`);
                    console.log(error)
                })
            this.buttonLoading = false
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
                    this.formFields.unit = data.unit
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
                    this.dataLoaded = true
                }
                else (console.log(err))
            }

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