import requests as r
from django.conf import settings


class BasicAPIRequest:
    def __init__(self):
        self.api_url_postfix = 'api/v1/'
        self.base_url = settings.BACKEND_API_BASE_URL + self.api_url_postfix
        self.data_dict = {
            "error": None,
            "response_data": [
                {
                    'id': 10,
                    'name': 'Django',
                    'sku': '123',
                    'supplier_id': None,
                    'category_id': 1001,
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

    @staticmethod
    def add_get_params(url: str, **kwargs):
        """
        Adds GET arguments to URL.
        http://example.url -> http://expample.url?kwarg1=value&kwarg2=value&...&kwargN=value
        get url arguments should be passed as kwargs
        """
        print('KWARGS: ', kwargs)
        if len(kwargs) == 0:
            return url
        else:

            url = url + '?'
            for key, value in kwargs.items():
                if value:
                    print(f'appending {key}={value}')
                    url = f'{url}{key}={value}&'
            return url[:-1]

    def get_products(self, page=None, size=None) -> dict:
        try:
            url = self.base_url + 'items'
            if page or size:
                url = BasicAPIRequest.add_get_params(url, page=page, size=size)
            print('sending GET to: ', url)
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
            print("trying to POST", request.data)
            response = r.post(url, json=request.data)
            print("POST presponse: ", response.text, response.status_code)
            return response.json()
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data

    def put_update_product(self, request, pk: int) -> dict:
        try:
            url = f'{self.base_url}items/{pk}/'
            print("trying to PUT", request.data)
            response = r.put(url, json=request.data)
            print("PUT presponse: ", response.text, response.status_code)
            return response.json()
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data

    def delete_product(self, pk: int):
        try:
            url = f'{self.base_url}items/{pk}/'
            response = r.delete(url, timeout=4)
            if response.ok:
                return response
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def get_categories(self):
        try:
            url = self.base_url + 'categories/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def get_category(self, pk: int):
        try:
            url = f'{self.base_url}categories/{pk}/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def put_update_category(self, request, pk: int) -> dict:
        try:
            url = f'{self.base_url}categories/{pk}/'
            print("trying to PUT", request.data)
            response = r.put(url, json=request.data)
            print("PUT presponse: ", response.text, response.status_code)
            return response.json()
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data

    def get_suppliers(self):
        try:
            url = self.base_url + 'suppliers/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def get_supplier(self, pk: int):
        try:
            url = f'{self.base_url}suppliers/{pk}/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def put_update_supplier(self, request, pk: int) -> dict:
        try:
            url = f'{self.base_url}suppliers/{pk}/'
            print("trying to PUT", request.data)
            response = r.put(url, json=request.data)
            print("PUT presponse: ", response.text, response.status_code)
            return response.json()
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data

    def get_storehouses(self):
        try:
            url = self.base_url + 'storehouses/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def get_storehouse(self, pk: int):
        try:
            url = f'{self.base_url}storehouses/{pk}/'
            response = r.get(url, timeout=4)
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
        return self.data_dict

    def put_update_storehouse(self, request, pk: int) -> dict:
        try:
            url = f'{self.base_url}storehouses/{pk}/'
            print("trying to PUT", request.data)
            response = r.put(url, json=request.data)
            print("PUT presponse: ", response.text, response.status_code)
            return response.json()
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data
