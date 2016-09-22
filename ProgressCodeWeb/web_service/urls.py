from django.conf.urls import url, include
from tastypie.api import Api
from web_service.api import TutorResource

v1_api = Api(api_name='v1')

# Registro de cada resource
v1_api.register(TutorResource())

urlpatterns = [
    url(r'^api/', include(v1_api.urls))
]
