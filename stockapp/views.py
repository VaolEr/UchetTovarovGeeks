from rest_framework.decorators import api_view
from rest_framework.response import Response
from stockapp.api import BasicAPIRequest, APIRequest
from django.shortcuts import render


def index_view(request):
    return render(request, 'stockapp/index.html')


@api_view(['GET'])
def product_list_view(request):
    url = '/items'
    token = request.headers.get('Authorization', None)
    page = request.GET.get('page', None)
    size = request.GET.get('size', None)

    api_request = APIRequest(url, token, page=page, size=size)
    return api_request.GET()


@api_view(['GET', 'POST', 'DELETE', 'PUT'])
def product_view(request, pk: int):
    url = f'/items/{pk}/'
    token = request.headers.get('Authorization', None)
    api_request = APIRequest(url, token)
    if request.method == 'GET':     # CRUD - read
        return api_request.GET()

    elif request.method == "PUT":   # CRUD - update
        return api_request.PUT(request.data)

    elif request.method == "DELETE":    # CRUD - delete
        return api_request.DELETE()


@api_view(['POST'])
def create_view(request):
    url = '/items'
    token = request.headers.get('Authorization', None)
    api_request = APIRequest(url, token)
    return api_request.POST(request.data)


@api_view(['GET'])
def categories_list_view(request):
    url = '/categories/'
    token = request.headers.get('Authorization', None)
    api_request = APIRequest(url, token)
    return api_request.GET()


@api_view(['GET', 'POST', 'PUT'])
def categories_view(request, pk: int):
    url = f'/categories/{pk}'
    token = request.headers.get('Authorization', None)
    api_request = APIRequest(url, token)
    if request.method == 'GET':     # CRUD - read
        return api_request.GET()

    elif request.method == "PUT":   # CRUD - update
        return api_request.PUT(request.data)


@api_view(['GET'])
def suppliers_list_view(request):
    url = '/suppliers/'
    token = request.headers.get('Authorization', None)
    api_request = APIRequest(url, token)
    return api_request.GET()


@api_view(['GET', 'POST', 'PUT'])
def suppliers_view(request, pk: int):
    url = f'/suppliers/{pk}'
    token = request.headers.get('Authorization', None)
    api_request = APIRequest(url, token)
    if request.method == 'GET':  # CRUD - read
        return api_request.GET()

    elif request.method == "PUT":  # CRUD - update
        return api_request.PUT(request.data)


@api_view(['GET', 'POST'])
def storehouses_list_view(request):
    url = '/storehouses/'
    token = request.headers.get('Authorization', None)
    api_request = APIRequest(url, token)
    return api_request.GET()


@api_view(['GET', 'POST', 'DELETE', 'PUT'])
def storehouses_view(request, pk: int):
    url = f'/storehouses/{pk}'
    token = request.headers.get('Authorization', None)
    api_request = APIRequest(url, token)
    if request.method == 'GET':  # CRUD - read
        return api_request.GET()

    elif request.method == "PUT":  # CRUD - update
        return api_request.PUT(request.data)


@api_view(['POST'])
def login(request):
    url = '/auth/login'
    api_request = APIRequest(url, None)
    return api_request.POST(request.data)

