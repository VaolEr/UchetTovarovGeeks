<template>
<div class="auth-form-container">
    <el-form 
    class="auth-form"
    ref="form" 
    :model="formFields"
    label-position="left"
    size='small'
    width="400">

        <el-form-item>
            <el-input v-model="formFields.email" >
             </el-input>
        </el-form-item>

        <el-form-item>
            <el-input type="password" v-model="formFields.password" >
            </el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm">Log in</el-button>
        </el-form-item>
    
    </el-form>
</div>
</template>

<script>
export default {
    name: 'AuthFormView',
    data() {
        return {
            formFields: {
                email: '',
                password: ''
            }
            
        }
    },
    methods:{
        submitForm() {
            this.$axios
            .post('stock/login', this.formFields)
            .then(response => {
                this.$store.commit('logIn', response.data.token)
                this.$router.push({name: 'index'})
            })
            .catch(err => console.log(err))
        },

    }
    
}
</script>

<style scoped>

.auth-form{
    max-width: 300px;
    margin: 0 auto
}
</style>