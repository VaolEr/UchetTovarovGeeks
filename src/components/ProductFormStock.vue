<template>
    <el-form-item v-if="dataLoaded" label="Stock">

        <product-form-stock-el-input 
        v-for="stock in stocks" 
        :key="stock.id" 
        v-bind:storehouse_name="stock.name"
        v-bind:storehouse_id="stock.id"
        v-bind:quantity.sync="stock.quantity"
        v-bind:disabled="product_id !== null"
        />
    
    </el-form-item>
</template>

<script>
import ProductFormStockElInput from "./ProductFormStockElInput"

export default {
    name: 'ProductFormStock',
    components: {ProductFormStockElInput},
    props: ['product_id', 'storehouses-balance'],
    data() {
        return {
            dataLoaded: false,
            stocks: []  
        }
    },
    methods: {
        loadStorehouses(){
            this.$axios
                .get(`stock/storehouses/`)
                .then(response => {         
                    
                    let data = response.data.response_data
                    this.stocks = data
                    this.loadQuantity(this.storehousesBalance)
                    
                    this.dataLoaded = true
                })
                .catch(error => {
                    console.log(error)
                })
        },
        updateQuantity() {
            this.$emit('update:storehouses-balance', this.stocks)
        },
        loadQuantity(arr){ // [{storehouse_id: 1000, quantity: 5}]
        
            this.stocks.forEach(localItem =>{
                localItem["quantity"] = 0
                localItem["storehouse_id"] = localItem.id // Фикс нэйминга storehouse.ID в API
                arr.forEach(item =>{
                    if (localItem.id === item.storehouse_id) {localItem["quantity"]=item.quantity}
                })
            })
        }
    },
    mounted(){
        this.loadStorehouses()
    },
    watch: {
        stocks: 'updateQuantity'
    }


}
</script>

<style scoped>

</style>