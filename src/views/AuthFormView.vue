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
            <el-button type="primary" @click="submitForm" :loading="loadingData">Log in</el-button>
        </el-form-item>
    
    </el-form>
</div>
</template>

<script>
export default {
    name: 'AuthFormView',
    data() {
        return {
            loadingData: false,
            formFields: {
                email: '',
                password: ''
            }
            
        }
    },
    methods:{
        submitForm() {
            this.loadingData = true
            this.$axios
            .post('stock/login', this.formFields)
            .then(response => {
                this.loadingData = false
                this.$store.commit('logIn', response.data.token)
                this.$router.push({name: 'index'})
            })
            .catch(err => {
                if (err.response.status !== 403) {
                    this.$notify.error(`Ошибка соединения с сервером. Код ошибки ${err.response.status}`)
                }
                this.loadingData = false
                console.log(err)
                })
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