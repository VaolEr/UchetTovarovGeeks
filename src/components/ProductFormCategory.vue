<template>
    <el-form-item v-if="dataLoaded" label = "Categories" >
        <el-radio-group v-model="selectedCategory">
            <el-radio-button 
            v-for="category in allCategories" 
            :key="category.id" 
            :label="category.name"
            >
            </el-radio-button>      
        </el-radio-group>
    </el-form-item> 
</template>

<script>
export default {
    name: "ProductFormCategory",
    props: ['selectedCategories'],
    data() {
        return {
            dataLoaded: false,
            allCategories: [],     
        }
    },
    computed: {
        selectedCategory: {
            get: function() {
                try {
                    let categoryName = this.selectedCategories[0].name // we need to pass string to radio button model. it will return label value
                    return categoryName
                    }
                catch {
                    return null
                }
                
                },
            set: function(newValue) {
                
                let categoryArr = []
                categoryArr[0] = this.getObjectByName(newValue)
                this.updateSelectedCategoty(categoryArr)
                
                }
            }
    },
    methods: {
        loadData(){
            try {
                this.$axios
                .get(`stock/categories/`)
                .then(response => {          
                    let data = response.data.data
                    this.allCategories = data
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
        updateSelectedCategoty (newValue) {
            this.$emit('update:selectedCategories', newValue)
        },
        getObjectByName(name) {
            let obj = this.allCategories.find(elem => elem.name === name)
            return obj
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