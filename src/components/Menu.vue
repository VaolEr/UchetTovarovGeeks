<template>
    <el-menu default-active="1">
        <div v-for="(item, index) in menuItems" :key="index">
            <router-link 
            :to="item.routName" 
            v-if="item.access.includes($store.getters.userRole)">
                <el-menu-item >
                    <i :class='item.icon'></i>
                    {{ item.title }} 
                </el-menu-item>
            </router-link>
        </div>
    </el-menu>
</template>

<script>
export default {
    name: 'Menu',
    data() {
        return{
            menuItems: [
                {title: 'Get product list', icon:'el-icon-document', routName: {name: 'ProductList'}, access: ['ADMIN', 'USER']},
                {title: 'Create product', icon:'el-icon-document-add', routName: {name: 'ProductFormView'}, access: ['ADMIN']},
                {title: 'Entities', icon:'el-icon-document-add', routName: {name: 'UniversalListView'}, access: ['ADMIN']},
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