from django.conf.urls import url, include
from tastypie.api import Api

from web_service.api import TutorResource
from web_service.api import InscritoResource
from web_service.api import EquipeResource
from web_service.api import EquipeTutorResource

nome_api = Api(api_name='webservice')

# Registro de cada resource
nome_api.register(TutorResource())
nome_api.register(InscritoResource())
nome_api.register(EquipeResource())
nome_api.register(EquipeTutorResource())

urlpatterns = [
    url(r'^api/', include(nome_api.urls))
]
