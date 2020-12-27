<template>
    <el-menu default-active="1">
        <el-menu-item v-for="(item, index) in menuItems" :key="index">
            
            <router-link :to="item.routName">
                <i :class='item.icon'></i>
                {{ item.title }} 
            </router-link>
            
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: 'Menu',
    data() {
        return{
            menuItems: [
                {title: 'Get product list', icon:'el-icon-document', routName: {name: 'productList'}},
                {title: 'Create product', icon:'el-icon-document-add', routName: {name: 'ProductFormView'}}
                ],
        }
    },
    methods: {
        
    }

}
</script>

<style scoped>

</style>