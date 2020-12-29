import requests as r
from django.conf import settings


class BasicAPIRequest:
    def __init__(self):
        self.base_url = settings.BACKEND_API_BASE_URL
        self.data_dict = {
            "error": None,
            "response_data": [
                {
                    'id': 10,
                    'name': 'Django',
                    'sku': '123',
                    'supplier_id': None,
                    'category_id':  1001,
                    'item_storehouses': None
                 },
                {
                    'id': 20,
                    'name': 'Python',
                    'sku': '#mag321',
                    'supplier_id': None,
                    'category_id': 1011,
                    'item_storehouses': None
                 }
            ],
        }

    def get_products(self) -> dict:
        try:
            url = self.base_url+'items/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def get_product(self, pk: int):
        try:
            url = f'{self.base_url}items/{pk}/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def post_create_product(self, request) -> dict:
        try:
            url = f'{self.base_url}items/'
            # error = {'error': None}
            # response = {**error, **request.data}
            print("trying to POST", request.data)
            response = r.post(url, json=request.data)
            print("POST presponse: ", response.text, response.status_code)
            return response.json()
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data

    def post_update_product(self, request) -> dict:
        pass

    def delete_product(self, pk: int) -> dict:
        try:
            url = f'{self.base_url}items/{pk}/'
            response = r.delete(url, timeout=4)
            if response.ok:
                return response.json()
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def get_categories(self):
        try:
            url = self.base_url+'categories/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict


