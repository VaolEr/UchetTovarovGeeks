<template>
<div>
    <el-radio-group v-model="selected">
        <el-radio-button 
        v-for="entity in entities" :key="entity.name" 
         v-bind:label="entity"
         
         >
            {{entity.name}}
        </el-radio-button>
        
    </el-radio-group>
    <universal-list-table class="table" v-bind:selected="selected"/>
</div> 
</template>

<script>
import UniversalListTable from '../components/UniversalListTable.vue'
export default {
  components: { UniversalListTable },
    name: "UniversalListView",
    data(){
        return{
            
            entities: [
                {name: "SUPPLIERS", url:"suppliers"},
                {name: "CATEGORIES", url:"categories"},
                {name: "STOREHOUSES", url:"storehouses"}
            ],
            selected: {name: "SUPPLIERS", url:"suppliers"},
        }
    }
    
}
</script>

<style scoped>
.table {
    margin-top: 5px;
}
</style>