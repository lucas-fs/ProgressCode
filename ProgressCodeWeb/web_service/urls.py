from django.conf.urls import url, include
from tastypie.api import Api

from web_service.api import TutorResource
from web_service.api import InscritoResource
from web_service.api import EquipeResource
from web_service.api import EquipeTutorResource
from web_service.api import EventoResource
from web_service.api import EventoEquipeResource
from web_service.api import EventoInscritoResource
from web_service.api import EncontroResource
from web_service.api import AtividadeResource
from web_service.api import FeedbackResource

nome_api = Api(api_name='webservice')

# Registro de cada resource
nome_api.register(TutorResource())
nome_api.register(InscritoResource())
nome_api.register(EquipeResource())
nome_api.register(EquipeTutorResource())
nome_api.register(EventoResource())
nome_api.register(EventoEquipeResource())
nome_api.register(EventoInscritoResource())
nome_api.register(EncontroResource())
nome_api.register(AtividadeResource())
nome_api.register(FeedbackResource())

urlpatterns = [
    url(r'^api/', include(nome_api.urls))
]
