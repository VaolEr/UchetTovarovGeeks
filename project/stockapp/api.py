import requests as r
from django.conf import settings


class BasicAPIRequest:
    def __init__(self):
        self.base_url = settings.BACKEND_API_BASE_URL
        self.test_dict = {
            "ERROR": "Request failed. Providing test json",
            "Err_desc": "",
            "test_product_list": [
                {"id": "1", "article": "ART1", "NAME": "Prod1", "Stock": "1"},
                {"id": "2", "article": "ART2", "NAME": "Prod2", "Stock": "2"},
                {"id": "3", "article": "ART3", "NAME": "Prod3", "Stock": "3"},
            ]
        }

    def get_products(self) -> dict:
        try:
            url = self.base_url+'products'
            response = r.get(url)
            print(response.status_code)
            if response.ok:
                return response.json()
        except Exception as e:
            self.test_dict["Err_desc"] = e.__repr__()
        return self.test_dict

    def post_create_product(self, request) -> dict:
        try:
            response = {**self.test_dict, **request.data}
            return response
        except Exception as e:
            self.test_dict["Err_desc"] = e.__repr__()
            return request.data

    def post_update_product(self, request) -> dict:
        pass

    def delete_product(self) -> dict:
        pass


