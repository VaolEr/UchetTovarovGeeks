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
      <el-button
        size="mini"
        @click="handleEdit(scope.row.id)"
      >
          Edit
      </el-button>
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
  props: {
    msg: String
  },
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
                    this.products = response.data.product_list
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
          console.log(id)
          console.log(this)
        },
        handleDelete(id) {
          console.log(id)
        }
    },
    created() {
        this.get_product_list()
    }
}
</script>

<style scoped>


</style>
