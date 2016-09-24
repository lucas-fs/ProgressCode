from django.conf.urls import url, include
from tastypie.api import Api
from web_service.api import TutorResource

nome_api = Api(api_name='webservice')

# Registro de cada resource
nome_api.register(TutorResource())

urlpatterns = [
    url(r'^api/', include(nome_api.urls))
]
