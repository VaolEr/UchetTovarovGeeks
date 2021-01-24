<template>
    <el-button 
    class="logout-button" 
    type="danger" 
    icon="el-icon-switch-button" 
    circle
    @click="logOut">
    </el-button>
</template>

<script>
export default {
    name: 'LogoutButton',
    data() {
        return {

        }
    },
    methods: {
        logOut() {
            this.$store.commit('logOut')
            this.$router.push( {name: 'login-form' })
        }
    }

}
</script>

<style scoped>
    

</style>