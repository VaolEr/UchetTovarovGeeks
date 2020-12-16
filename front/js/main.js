const app = Vue.createApp({
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
            axios
                .get('http://127.0.0.1:8001/stock/')
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
            products = [];
            product_keys = [];
        },
    },
    created() {
        this.get_product_list()
    }
})






const mountedApp = app.mount("#app")