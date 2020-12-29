<template>
        <el-checkbox-group v-model="selectedCategories">
            <el-checkbox-button 
            v-for="category in allCategories" 
            :key="category.id" 
            :label="category.name"
            @change="passData">
            </el-checkbox-button>      
        </el-checkbox-group>
</template>

<script>
export default {
    name: "ProductFormCategory",
    props: ['selectedCategories'],
    data() {
        return {
            
            allCategories: [],
        }
    },
    computed: {
        selectedCategoryObjects() {
            let categoryObjects = []
            this.selectedCategories.forEach(element => {
                categoryObjects.push(this.getObjectByName(element))
            });
            return categoryObjects
        }
    },
    methods: {
        loadData(){
            try {
                this.$axios
                .get(`stock/categories/`)
                .then(response => {          
                    let data = response.data.response_data
                    this.allCategories = data
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
        getObjectByName(name) {
            let obj = this.allCategories.find(elem => elem.name === name)
            return obj
        },
        passData(){
            this.$emit('data-exchange', 'categories', this.selectedCategoryObjects)
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