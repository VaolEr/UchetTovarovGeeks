<template>
    <el-menu default-active="1">
        <el-menu-item v-for="(item, index) in menuItems" :key="index" @click="changeMainComp(item.componentName)">
            <i :class='item.icon'></i>
            {{ item.title }} 
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: 'Menu',
    data() {
        return{
            menuItems: [
                {title: 'Get product list', componentName: 'GetListProducts', icon:'el-icon-document'},
                {title: 'Create product', componentName: 'CreateProduct', icon:'el-icon-document-add'}
                ],
        }
    },
    methods: {
        changeMainComp(componentName) {
            this.$emit('change-component', componentName)
        }
    }

}
</script>

<style scoped>

</style>