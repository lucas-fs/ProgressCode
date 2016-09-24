from tastypie.resources import ModelResource
from tastypie.constants import ALL
from tastypie.authorization import Authorization
from tastypie import fields

from web_service.models import Tutor
from web_service.models import Equipe
from web_service.models import Inscrito
from web_service.models import Evento
from web_service.models import EquipeTutor
from web_service.models import EventoEquipe
from web_service.models import EventoInscrito


class TutorResource(ModelResource):
    class Meta:
        queryset = Tutor.objects.all()
        resource_name = "tutores"
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'nome': ALL,
            'email': ALL
        }

class EquipeResource(ModelResource):
    tutores = fields.ToManyField('web_service.api.resources.EquipeTutorResource', 'equipetutor_set', related_name='equipe', full=True)
    # o parametro equipetutor_set nao se refere ao db_table ou resource_name do model?

    class Meta:
        queryset = Equipe.objects.all()
        resource_name = "equipe"
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'descricao': ALL,
            'membros': ALL
        }

class EquipeTutorResource(ModelResource):
    tutor = fields.ToOneField('mquiz.api.resources.TutorResource', 'tutor', full=True)

    class Meta:
        queryset = EquipeTutor.objects.all()
        allowed_methods = ['get', 'post']
        authorization = Authorization()

class InscritoResource(ModelResource):
    class Meta:
        queryset = Inscrito.objects.all()
        resource_name = "inscrito"
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'nome': ALL,
            'data_nasc': ALL,
            'escola': ALL
        }

class EventoResource(ModelResource):
    equipes = fields.ToManyField('web_service.api.resources.EventoEquipeResource', 'eventoequipe_set', related_name='evento', full=True)
    inscritos = fields.ToManyField('web_service.api.resources.EventoInscritoResource', 'eventoinscrito_set', related_name='evento')

    class Meta:
        queryset = Evento.objects.all()
        resource_name = "evento"
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'nome_evento': ALL,
            'descricao': ALL,
        }

class EventoEquipeResource(ModelResource):
    equipe = fields.ToOneField('mquiz.api.resources.EquipeResource', 'equipe', full=True)
    # adicionar mais um atributo para outro lado da relacao?

    class Meta:
        queryset = EventoEquipe.objects.all()
        allowed_methods = ['get', 'post']
        authorization = Authorization()

class EventoInscritoResource(ModelResource):
    class Meta:
        pass
