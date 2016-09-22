<<<<<<< HEAD
from django.conf.urls import url, include
from tastypie.api import Api
from web_service.api import TutorResource

v1_api = Api(api_name='v1')

# Registro de cada resource
v1_api.register(TutorResource())

urlpatterns = [
    url(r'^api/', include(v1_api.urls))
]
=======
from django.conf.urls import include, url
from . import views

urlpatterns = [
	url(r'^$', views.csv_reader)
]
>>>>>>> 212dd7b1dc8866834f637f38de3ecc3bd6f14720
