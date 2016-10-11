from tastypie.resources import ModelResource
from tastypie.constants import ALL
from tastypie.authorization import Authorization
from tastypie import fields

# import das models
from web_service.models import Tutor
from web_service.models import Equipe
from web_service.models import Inscrito
from web_service.models import Evento
from web_service.models import EquipeTutor
from web_service.models import EventoEquipe
from web_service.models import EventoInscrito


# LADO 1  - Entidade TUTOR
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

# LADO 1 - Entidade Equipe
class EquipeResource(ModelResource):
    tutores = fields.ToManyField('web_service.api.EquipeTutorResource', 'equipetutor_set', related_name='equipe', full=True)
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

# NxN - Relacao Tutor_Equipe
class EquipeTutorResource(ModelResource):
    tutor = fields.ToOneField('web_service.api.TutorResource', 'tutor', full=True, use_in = 'list')
    equipe = fields.ToOneField('web_service.api.EquipeResource', 'equipe', full=True, use_in = 'list')
    class Meta:
        resource_name = "equipe_tutor"
        queryset = EquipeTutor.objects.all()
        allowed_methods = ['get', 'post']
        authorization = Authorization()

# LADO 1 - Entidade Inscrito
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

# LADO 1 - Entidade Evento
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

# NxN - Relacao Evento_Equipe
class EventoEquipeResource(ModelResource):
    equipe = fields.ToOneField('mquiz.api.resources.EquipeResource', 'equipe', full=True)
    # adicionar mais um atributo para outro lado da relacao?

    class Meta:
        queryset = EventoEquipe.objects.all()
        allowed_methods = ['get', 'post']
        authorization = Authorization()
