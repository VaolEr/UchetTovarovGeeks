import requests as r
from django.conf import settings


class BasicAPIRequest:
    def __init__(self):
        self.base_url = settings.BACKEND_API_BASE_URL
        self.data_dict = {
            "error": "",
            "product_list": [
                {
                     "id": 1,
                     "article": "New Article 1",
                     "name": "New Name 1",
                     "typeID": 1,
                     "supplierID": 1,
                     "warehouseID": 1
                 },
                {
                    "id": 2,
                    "article": "New Article 2",
                    "name": "New Name 2",
                    "typeID": 2,
                    "supplierID": 2,
                    "warehouseID": 2
                },
                ],
        }

    def get_products(self) -> dict:
        try:
            url = self.base_url+'products/'
            response = r.get(url, timeout=1)
            if response.ok:
                self.data_dict["product_list"] = response.text
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def post_create_product(self, request) -> dict:
        try:
            response = {**self.data_dict, **request.data}
            return response
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data

    def post_update_product(self, request) -> dict:
        pass

    def delete_product(self) -> dict:
        pass


