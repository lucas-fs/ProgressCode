from tastypie.resources import ModelResource
from tastypie.constants import ALL, ALL_WITH_RELATIONS
from tastypie.authorization import Authorization
from tastypie import fields

# import das models
from web_service.models import Tutor
from web_service.models import Equipe
from web_service.models import Inscrito
from web_service.models import Evento
from web_service.models import Atividade
from web_service.models import Encontro
from web_service.models import EquipeTutor
from web_service.models import EventoEquipe
from web_service.models import EventoInscrito
from web_service.models import Feedback

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
    #tutores = fields.ToManyField('web_service.api.EquipeTutorResource', 'equipetutor_set', related_name='equipe', full=True)

    class Meta:
        queryset = Equipe.objects.all()
        resource_name = "equipe"
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'descricao': ALL
        }

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
    class Meta:
        queryset = Evento.objects.all()
        resource_name = "evento"
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'nome_evento': ALL,
            'descricao': ALL
        }

class EncontroResource(ModelResource):
    #evento = fields.ToOneField('web_service.api.EventoResource', 'evento', full=True, use_in = 'list')
    evento_id = fields.IntegerField(attribute="evento__id")

    class Meta:
        queryset = Encontro.objects.all()
        resource_name = "encontros"
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'data_realizao': ALL,
            'evento_id': ALL_WITH_RELATIONS
        }

class AtividadeResource(ModelResource):
    #encontro = fields.ToOneField('web_service.api.EncontroResource', 'encontro', full=True, use_in = 'list')
    encontro_id = fields.IntegerField(attribute="encontro__id")

    class Meta:
        queryset = Atividade.objects.all()
        resource_name = "atividades"
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'descricao': ALL,
            'encontro_id': ALL_WITH_RELATIONS
        }

class EventoEquipeResource(ModelResource):
    equipe = fields.ToOneField('web_service.api.EquipeResource', 'equipe', full=True, use_in = 'list')
    evento = fields.ToOneField('web_service.api.EventoResource', 'evento', full=True, use_in = 'list')

    class Meta:
        resource_name = "evento_equipe"
        queryset = EventoEquipe.objects.all()
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'equipe': ALL_WITH_RELATIONS,
            'evento': ALL_WITH_RELATIONS
        }

class EventoInscritoResource(ModelResource):
    #evento = fields.ToOneField('web_service.api.EventoResource', 'evento', full=True, use_in = 'list')
    #inscrito = fields.ToOneField('web_service.api.InscritoResource', 'inscrito', full=True, use_in = 'list')
    evento_id = fields.IntegerField(attribute="evento__id")
    inscrito_id = fields.IntegerField(attribute="inscrito__id")

    class Meta:
        resource_name = "evento_inscrito"
        queryset = EventoInscrito.objects.all()
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'inscrito': ALL_WITH_RELATIONS,
            'evento': ALL_WITH_RELATIONS
        }

class EquipeTutorResource(ModelResource):
    tutor = fields.ToOneField('web_service.api.TutorResource', 'tutor', full=True, use_in = 'list')
    equipe = fields.ToOneField('web_service.api.EquipeResource', 'equipe', full=True, use_in = 'list')

    class Meta:
        resource_name = "equipe_tutor"
        queryset = EquipeTutor.objects.all()
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'equipe': ALL_WITH_RELATIONS,
            'tutor': ALL_WITH_RELATIONS
        }

class FeedbackResource(ModelResource):
    tutor = fields.ToOneField('web_service.api.TutorResource', 'tutor', full=True, use_in = 'list')
    inscrito = fields.ToOneField('web_service.api.InscritoResource', 'inscrito', full=True, use_in = 'list')
    atividade = fields.ToOneField('web_service.api.AtividadeResource', 'atividade', full=True, use_in = 'list')

    class Meta:
        resource_name = "feedbacks"
        queryset = Feedback.objects.all()
        allowed_methods = ['get', 'post']
        authorization = Authorization()
        filtering = {
            'tutor': ALL_WITH_RELATIONS,
            'inscrito': ALL_WITH_RELATIONS,
            'atividade': ALL_WITH_RELATIONS,
            'status': ALL,
            'timestamp': ALL
        }
