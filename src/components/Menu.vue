<template>
    <el-menu default-active="1">
        
            <router-link v-for="(item, index) in menuItems" :key="index" :to="item.routName">
                <el-menu-item >
                    <i :class='item.icon'></i>
                    {{ item.title }} 
                </el-menu-item>
            </router-link>

    </el-menu>
</template>

<script>
export default {
    name: 'Menu',
    data() {
        return{
            menuItems: [
                {title: 'Get product list', icon:'el-icon-document', routName: {name: 'ProductList'}},
                {title: 'Create product', icon:'el-icon-document-add', routName: {name: 'ProductFormView'}}
                ],
        }
    },
    methods: {

    }

}
</script>

<style scoped>
    a {
        color: black;
        text-decoration: none;
    }

</style>