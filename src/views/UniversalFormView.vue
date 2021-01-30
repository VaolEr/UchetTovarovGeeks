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
            <el-button v-show="false" v-if="!formFields.id" type="success" @click="onSave" :loading="buttonLoading">Save</el-button>
            <el-button v-if="formFields.id" type="primary" @click="onUpdate($route.params.type, formFields.id)" :loading="buttonLoading">Update</el-button>
            <el-button disabled v-if="formFields.id" type="danger" @click="onDelete(formFields.id)">Delete</el-button>
        </el-form-item>
</el-form>
</template>

<script>

export default {

    name: "UniversalFormView",
    data(){
        return{
            buttonLoading: false,
            itemType: this.$route.params.type,
            formFields: {}
        }
    },
    methods: {
        loadData(url, id){
            this.$axios
                .get(`stock/${url}/${id}/`)
                .then(response => {
            
                    let data = response.data.data
                    this.formFields = data 

                })
                .catch(error => {
                    console.log('loadData error: ', error)
                })
            },
        onSave(){},
        onUpdate(url, id){
            this.buttonLoading = true
            this.$axios
            .put(`stock/${url}/${id}/`, this.formFields)
            .then(() => {
                this.buttonLoading = false
                this.$notify.success(`Item updated`);
                })
            .catch(error => {
                this.$notify.error(`ERROR`);
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