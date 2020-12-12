import requests


class BasicAPIRequest:
    def __init__(self):
        self.base_url = "url"
        self.test_dict = {
            "test_key": "test_value",
            "test_list": ["list_value_1", "list_value_2"],
            "test_dict": {"dict_key_1": "dict_value_1",
                          "dict_key_2": "dict_value_2"}
        }

    def get_products(self) -> dict:
        return self.test_dict

    def post_create_product(self, request) -> dict:
        try:
            response = {**self.test_dict, **request.data}
            return response
        except Exception as e:
            print(e)
            return request.data

    def post_update_product(self, request) -> dict:
        pass

    def delete_product(self) -> dict:
        pass


