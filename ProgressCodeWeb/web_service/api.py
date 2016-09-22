from tastypie.resources import ModelResource
from tastypie.constants import ALL
from tastypie.authorization import Authorization

from web_service.models import Tutor


class TutorResource(ModelResource):
    class Meta:
        queryset = Tutor.objects.all()
        resource_name = 'tutores'
        allowed_methods = ['get', 'post']
        authorization = Authorization()
