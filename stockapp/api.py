import requests as r
from django.conf import settings


class BasicAPIRequest:
    def __init__(self):
        self.base_url = settings.BACKEND_API_BASE_URL
        self.data_dict = {
            "error": "",
            "product_list": [
                {
                    'id': 1001,
                    'name': 'Pen',
                    'sku': '123',
                    'supplier': None,
                    'category': {'id': 1001, 'name': 'stationery'},
                    'item_storehouses': None
                 },
                {
                    'id': 1011,
                    'name': 'Murzilka',
                    'sku': '#mag321',
                    'supplier': None,
                    'category': {'id': 1011, 'name': 'magazines'},
                    'item_storehouses': None
                 }
            ],
        }

    def get_products(self) -> dict:
        try:
            url = self.base_url+'items/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["product_list"] = response.json()['data']
                print("get_products", type(response.json()), response.json()['data'])
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def get_product(self, slug: str):
        try:
            url = f'{self.base_url}items?name={slug}'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["product_list"] = response.json()['data']
                print("get_product", type(response.json()), response.json()['data'])
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def post_create_product(self, request) -> dict:
        try:
            error = {'error': None}
            response = {**error, **request.data}
            print(response)
            return response
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data


    def post_update_product(self, request) -> dict:
        pass

    def delete_product(self) -> dict:
        pass


