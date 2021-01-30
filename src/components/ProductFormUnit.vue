<template>
    <el-form-item v-if="dataLoaded" label="Units"> 
        <el-select v-model="selectedSup" value-key="id" placeholder="Select units">
            <el-option 
            v-for="item in unitsList"
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
    name: "ProductFormUnit",
    props: ['selectedUnit'],
    data() {
        return {
            unitsList: [],
            dataLoaded: false,
        }
    },
    computed: {
        selectedSup: {
            get: function() {
                try {
                    // matching selected unit in parent comp with units awailible for selection (unitsList)
                   let match = this.unitsList.find(elem => this.selectedUnit.id === elem.id)
                   return match 
                }
                catch (err) {
                    return null
                }
                
            },
            set: function(newValue) {
                this.$emit('update:selected-unit', newValue)
            }
        }
    },
    methods: {
        loadData(){
            try {
                this.$axios
                .get(`stock/units/`)
                .then(response => {          
                    let data = response.data.data
                    this.unitsList = data
                    this.dataLoaded = true
                })
                .catch(error => {
                    console.log(error)
                })
            } 
            catch (err){
                if (err.name == "TypeError") {
                    // Form launches in create mode   
                }
                else {
                    console.log(err)
                }
            }
            
        },

    },
    created() {
        this.loadData()
    }, 
    
}
</script>

<style scoped>

</style>