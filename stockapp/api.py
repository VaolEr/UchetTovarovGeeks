import requests as r
from django.conf import settings
from django.http import HttpResponseForbidden
from rest_framework.response import Response


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
        print('RESPONSE STATUS CODE:', response.status_code)
        if response.status_code == 403 or response.status_code == 401:
            return HttpResponseForbidden()
        else:
            return Response(status=response.status_code)

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
