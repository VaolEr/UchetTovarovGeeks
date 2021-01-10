<template>
  <el-table 
    :data="products"
    empty-text="Loading data..."
    border
    style="width: 100%">
    <el-table-column 
      prop="id"
      label="ID"
      width="100"
    >
    </el-table-column>

    <el-table-column 
      prop="name"
      label="NAME"
    >
    </el-table-column>

    <el-table-column 
      prop="sku"
      label="SKU"
    >
    </el-table-column>

    <el-table-column 
      prop="supplier.name"
      label="SUPPLIER"
    >
    </el-table-column>

    <!-- MAKE ME A COMPONENT WHEN PROUDCT WILL HAVE MULTIPLE CATEGORIES -->
    <el-table-column 
      prop="categories[0].name"
      label="CATEGORY"
    >
    </el-table-column>


    <el-table-column 
      label="Action"
      width="170"
    >
    <template slot-scope="scope">
      <router-link :to="{name:'ProductFormViewEdit', params: {id: scope.row.id}}">
        <el-button
          class="action_btn"
          size="mini"
        >
          Edit
        </el-button> 
      </router-link>
      <el-button
        class="action_btn"
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
        }
    },
    methods: {
        get_product_list() {
            this.clear_data()
            this.$axios
                .get('stock/')
                .then(response => {
                    this.products = response.data.response_data.content
                    
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
        handleDelete(id) {
          this.$axios
                .delete(`stock/product/${id}/`)
                .then(response => {
                    console.log("successfully deleted. Server responded: ", response)
                    this.products.splice(this.products.indexOf(item => item.id === id),1)
                    })
                .catch(error => {
                    console.log(error)
                    this.request_error = true
                })
        }
    },
    created() {
        this.get_product_list()
    }
}
</script>

<style scoped>
.action_btn {
  margin: 2px;
  min-width: 70px;
}

</style>
