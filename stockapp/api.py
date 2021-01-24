import requests as r
from django.conf import settings
from django.http import HttpResponseForbidden
from rest_framework.response import Response

# TODO delete BasicAPIRequest class if not needed.
class BasicAPIRequest:
    def __init__(self):
        self.api_url_postfix = 'api/v1/'
        self.base_url = settings.BACKEND_API_BASE_URL + self.api_url_postfix
        self.token = None
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

        if len(kwargs) == 0:
            return url
        else:

            url = url + '?'
            for key, value in kwargs.items():
                if value:

                    url = f'{url}{key}={value}&'
            return url[:-1]

    def get_products(self, page=None, size=None) -> dict:
        try:
            url = self.base_url + 'items'
            if page or size:
                url = BasicAPIRequest.add_get_params(url, page=page, size=size)
            response = r.get(url, timeout=4, headers={"Authorization": self.token})
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
            response = r.get(url, timeout=4, headers={"Authorization": self.token})
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
            response = r.get(url, timeout=4, headers={"Authorization": self.token})
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
            response = r.get(url, timeout=4, headers={"Authorization": self.token})
            if response.ok:
                self.data_dict["response_data"] = response.json()['data']
            else:
                return HttpResponseForbidden()
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
            print("PUT response: ", response.text, response.status_code)
            return response.json()
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data

    def post_login(self, request):
        try:
            url = f'{self.base_url}auth/login'
            print(f"trying to POST to {url} \n", request.data)
            response = r.post(url, json=request.data)
            print(f"POST response with code {response.status_code}: \n ", response.text)
            if response.ok:
                return response.json()
            else:
                return response
        except Exception as e:
            self.data_dict["error"] = e.__repr__()
            return request.data


class APIRequest:
    def __init__(self, request_path, token, *args, **kwargs):
        self.url = self.combine_url(request_path, **kwargs)
        self.api_url_postfix = 'api/v1/'
        self.base_url = settings.BACKEND_API_BASE_URL + self.api_url_postfix
        self.token = token
        self.data = {}

    @staticmethod
    def combine_get_params(**kwargs):
        """
        Turns **kwargs into GET arguments for URL.
        {kwarg1: value1, kwarg2:value2 ...} -> ?kwarg1=value1&kwarg2=value2&...&kwargN=value
        get url arguments should be passed as kwargs
        """

        if len(kwargs) == 0:
            return ''
        else:
            get_params = '?'
            for key, value in kwargs.items():
                if value:
                    get_params = f'{get_params}{key}={value}&'
            return get_params[:-1]

    @staticmethod
    def get_base_url_postfix():
        """
        Should make a request to API server to get actual URL postfix e.g. ( api/v1/ )
        :return: url postfix as string
        """
        return 'api/v1'

    def combine_url(self, request_path, **kwargs):
        url = f'{settings.BACKEND_API_BASE_URL}' \
              f'{APIRequest.get_base_url_postfix()}' \
              f'{request_path}' \
              f'{self.combine_get_params(**kwargs)}'
        print("URL COMBINED: ", url)
        return url

    def handle_response_error(self, response):
        if response.status_code == 403 or response.status_code == 401:
            return HttpResponseForbidden()

    def GET(self):
        try:
            response = r.get(self.url, timeout=4, headers={"Authorization": self.token})
            print(f"RESPONSE CODE {response.status_code}")
            if response.ok:
                self.data = response.json()
                status = response.status_code
            else:
                return self.handle_response_error(response)
        except Exception as e:
            self.data["error"] = e.__repr__()
            status = 418
        return Response(self.data, status=status)

    def POST(self, request_data):
        try:
            print("trying to POST", request_data)
            response = r.post(self.url, json=request_data, headers={"Authorization": self.token})
            if response.ok:
                self.data = response.json()
                status = response.status_code
                print("STATUS=", status)
            else:
                return self.handle_response_error(response)
        except Exception as e:
            print(e)
            self.data["error"] = e.__repr__()
            status = 418
        return Response(self.data, status=status)

    def PUT(self, request_data):
        try:
            print("trying to PUT", request_data)
            response = r.put(self.url, json=request_data, headers={"Authorization": self.token})
            print("RESPONSE: ", response.status_code, response.text)
            if response.ok:
                self.data = response.json()
                status = response.status_code
            else:
                return self.handle_response_error(response)
        except Exception as e:
            self.data["error"] = e.__repr__()
            status = 418
        return Response(self.data, status=status)

    def DELETE(self):
        try:
            response = r.delete(self.url, timeout=4, headers={"Authorization": self.token})
            print(f"RESPONSE CODE {response.status_code}")
            if response.ok:
                status = response.status_code
            else:
                return self.handle_response_error(response)
        except Exception as e:
            self.data["error"] = e.__repr__()
            status = 418
        return Response(self.data, status=status)
