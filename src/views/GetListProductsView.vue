<template>
  <el-table 
    v-if="products.length"
    :data="products"
    empty-text="No info"
    border
    style="width: 100%">
    <el-table-column 
      v-for="(item, index) in product_keys" v-bind:key="index"
      :prop="item"
      :label="item"
    >
    </el-table-column>

    <el-table-column 
      label="Action"
    >
    <template slot-scope="scope">
      <router-link :to="{name:'ProductFormViewEdit', params: {id: scope.row.id}}">
        <el-button
          size="mini"
        >
          Edit
        </el-button>
      </router-link>
      <el-button
        size="mini"
        type="danger"
        @click="handleDelete(scope.row.id)"
      >
          Delete
      </el-button>
    </template>
    </el-table-column>
    
  </el-table>
</template>

<script>
export default {
  name: 'GetListProducts',
  data() {
        return {
            loading: true,
            request_error: false,
            products: [],
            product_keys: []
        }
    },
    methods: {
        get_product_list() {
            this.clear_data()
            this.$axios
                .get('stock/')
                .then(response => {
                    this.products = response.data.response_data
                    this.product_keys = Object.keys(this.products[0])
                    })
                .catch(error => {
                    console.log(error)
                    this.request_error = true
                })
                .finally(() => (this.loading = false));
        },
        clear_data() {
            this.loading= true;
            this.request_error= false;
            this.products = [];
            this.product_keys = [];
        },
        handleEdit(id) {
          this.$emit('change-component', 'ProductForm', {id})
        },
        handleDelete(id) {
          this.$axios
                .delete(`stock/product/${id}/`)
                .then(response => {
                    this.products = response.data.response_data
                    this.product_keys = Object.keys(this.products[0])
                    })
                .catch(error => {
                    console.log(error)
                    this.request_error = true
                })
                .finally(() => (this.loading = false));
        }
    },
    created() {
        this.get_product_list()
    }
}
</script>

<style scoped>


</style>
