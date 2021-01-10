<template>
<el-form class="form" ref="form" :model="formFields" label-position="top" size='small'>
    <el-form-item>
        <el-select disabled v-model="itemType"></el-select>
    </el-form-item>
    <el-form-item
     v-for="(value, key) in formFields" :key="key">
        <el-input 
        v-model="formFields[key]"
        :disabled="key=='id'">
            <template slot="prepend">{{key}}</template>
        </el-input>
    </el-form-item>
    <el-form-item>
            <el-button v-show="false" v-if="!formFields.id" type="success" @click="onSave">Save</el-button>
            <el-button v-if="formFields.id" type="primary" @click="onUpdate($route.params.type, formFields.id)">Update</el-button>
            <el-button disabled v-if="formFields.id" type="danger" @click="onDelete(formFields.id)">Delete</el-button>
        </el-form-item>
</el-form>
</template>

<script>

export default {

    name: "UniversalFormView",
    data(){
        return{
            itemType: this.$route.params.type,
            formFields: {}
        }
    },
    methods: {
        loadData(url, id){
            this.$axios
                .get(`stock/${url}/${id}/`)
                .then(response => {
            
                    let data = response.data.response_data
                    this.formFields = data 

                })
                .catch(error => {
                    console.log('loadData error: ', error)
                })
            },
        onSave(){},
        onUpdate(url, id){
            this.$axios
            .put(`stock/${url}/${id}/`, this.formFields)
            .then(response => {
                console.log(response)
                })
            .catch(error => {
                    console.log(error)
                })
        },
        onDelete(){},
    },
    created(){
        this.loadData(this.$route.params.type, this.$route.params.id)
    }
    
}
</script>

<style scoped>
.form {
    width: 30%;
    margin-left:35%
}
</style>