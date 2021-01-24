<template>
    <el-input 
        
        :disabled="disabled"
        v-model="stockData.local_quantity"
        @input="updateParent"        
        >
            <template slot="prepend">{{ stockData.local_storehouse_name }}</template>   
        </el-input > 
</template>

<script>
export default {
    name: "ProductFormStockElInput",
    props: ['storehouse_name','id','quantity', 'disabled'],
    data() {
        let stockData = {
            local_storehouse_name: this.storehouse_name,
            local_id: this.id,
            local_quantity: this.quantity
        }
        return {
            stockData
        }
    },
    methods: {
        updateParent(){
            this.$emit('update:quantity', parseInt(this.stockData.local_quantity))
        }
    }
}
</script>

<style scoped>

</style>