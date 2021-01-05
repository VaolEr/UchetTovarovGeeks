<template>
    <el-form-item v-if="dataLoaded" label="Supplier"> 
        <el-select v-model="selectedSup" value-key="id" placeholder="Select supplier">
            <el-option 
            v-for="item in suppliersList"
            :key="item.id"
            :label="item.name" 
            :value="item">
                {{ item.name }}
            </el-option>
        </el-select>
    </el-form-item>
</template>

<script>
export default {
    name: "ProductFormSupplier",
    props: ['selectedSupplier'],
    data() {
        return {
            suppliersList: [],
            dataLoaded: false,
        }
    },
    computed: {
        selectedSup: {
            get: function() {
                try {
                    // matching selected supplier in parent comp with supplier awailible for selection (suppliersList)
                   let match = this.suppliersList.find(elem => this.selectedSupplier.id === elem.id)
                   return match 
                }
                catch (err) {
                    console.log("Something went wrong with selectedSup calculation", err)
                    return ""
                }
                
            },
            set: function(newValue) {
                this.$emit('update:selected-supplier', newValue)
            }
        }
    },
    methods: {
        loadData(){
            try {
                this.$axios
                .get(`stock/suppliers/`)
                .then(response => {          
                    let data = response.data.response_data
                    this.suppliersList = data
                    // console.log("SUPPLIERS LIST in AXIOS:  ",this.suppliersList)
                    // this.selectedElement = this.setSelected(this.selectedSupplier, this.suppliersList)
                    this.dataLoaded = true
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
            
        },
        updateSelectedElem(){
            // emitting update in parent comp
        },
        

    },
    created() {
        this.loadData()
    }, 
    
}
</script>

<style scoped>

</style>