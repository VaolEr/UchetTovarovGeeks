<template>
<el-table 
    v-if="loadedData"
    :data="loadedData"
    empty-text="Loading data..."
    border
    style="width: 100%">
    <el-table-column
    
    v-for="key in headers" :key="key"
    :sortable="key == 'id'"
    :prop="key"
    :label="key">
    </el-table-column>
    <el-table-column 
      label="Action"
      width="172"
      >
        <template slot-scope="scope">
            <router-link :to="{name:'UniversalFormView', params: {type: selected.url, id: scope.row.id}}">
                <el-button
                class="action_btn"
                size="mini"
                >
                Edit
                </el-button> 
            </router-link>
            <el-button
                class="action_btn"
                size="mini"
                type="danger"
                disabled
                @click="handleDelete(selected.url, scope.row.id)"
            >
                Delete
            </el-button>
        </template>
    </el-table-column>

</el-table>



    
</template>

<script>
export default {
    name: "UniversalListTable",
    props: ['selected'],
    data(){
        return{   
           loadedData: null,
           request_error: false          
        }
    },
    computed:{
        headers: function() {
            return Object.keys(this.loadedData[0])
        },
    },
    methods: {
        loadData(selectedUrl){
            this.$axios
                .get(`stock/${selectedUrl}/`)
                .then(response => {
                    this.loadedData = response.data.data
                    })
                .catch(error => {
                    console.log(error)
                    this.request_error = true
                })
        },
        handleDelete(url, id){
            this.$axios
                .delete(`stock/${url}/${id}/`)
                .then(response => {
                    console.log("successfully deleted. Server responded: ", response)
                    this.loadData
                    })
                .catch(error => {
                    console.log(error)
                    this.request_error = true
                })
        }
    },
    mounted() {
       this.loadData(this.selected.url)
    },
    watch: {
        selected: function (newValue) {
            this.loadData(newValue.url)
        }
    }
    
}
</script>

<style scoped>
.action_btn {
  margin: 2px;
  min-width: 70px;
}
</style>