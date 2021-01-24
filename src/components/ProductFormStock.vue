<template>
    <el-form-item v-if="dataLoaded" label="Stock">

        <product-form-stock-el-input 
        v-for="stock in stocks" 
        :key="stock.id" 
        v-bind:storehouse_name="stock.name"
        v-bind:id="stock.id"
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
                    this.stocks = response.data.data

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
        loadQuantity(arr){ // arr = [{id: 1000, quantity: 5}, {id:1001, quantity: 3}]
        
            this.stocks.forEach(localItem =>{
                console.log("LOCAL ITEM", localItem)
                
                localItem.quantity = 0
                arr.forEach(item =>{
                    if (localItem.id === item.id) {localItem.quantity=item.quantity}
                })
            })
        }
    },
    mounted(){
        this.loadStorehouses()
    },
    watch: {
        stocks: 'updateQuantity',
    }


}
</script>

<style scoped>

</style>