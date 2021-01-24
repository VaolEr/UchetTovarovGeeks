<template>
<div>
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
      width="172"
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
  <div v-observe-visibility="handleScrollToBottom">
    <el-button 
      class="load_indicator"
      type="primary" 
      v-if="page < totalPages" 
      :loading="loading" 
      size="small" 
      @click="getProductList"
      >
        Load more...
      </el-button>
  </div>
</div>
</template>

<script>
export default {
  name: 'GetListProducts',
  data() {
        return {
            loading: true,
            request_error: false,
            page: 0,
            totalPages: 0,
            pageSize: 10,
            products: [],
        }
    },
    methods: {
        getProductList() {
          this.loading = true
            this.$axios
                .get(`stock/?page=${this.page}&size=${this.pageSize}`)
                .then(response => {
                    this.products.push(...response.data.data.content)
                    this.totalPages = response.data.data.total_pages
                    })
                .catch(error => {
                    console.log(error)
                    this.request_error = true
                })
                .finally(() => {
                  this.loading = false 
                  this.page++
                  });
        },
        clear_data() {
            this.loading= true;
            this.request_error= false;
            this.products = [];
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
        },
        handleScrollToBottom(isVisible) {
          if (!isVisible) { return }
          if (this.loading) { return }
          if (this.page < this.totalPages){
            
            this.getProductList()
          }
        }
    },
    created() {
        this.getProductList()
    }
}
</script>

<style scoped>
.action_btn {
  margin: 2px;
  min-width: 70px;
}

.load_indicator{
  margin: 10px;
}

</style>
