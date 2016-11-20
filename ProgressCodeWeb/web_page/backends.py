from django.conf import settings
import django.contrib.auth.models
from web_service.models import Tutor

class EmailAuthBackend(object):
    '''
    Backend de autenticacao customizado para autenticar pelo campo de email do tutor
    '''

    def authenticate(self, email=None, password=None):
        try:
            tutor = Tutor.objects.get(email=email)
            if tutor.check_password(password):
                return tutor
        except Tutor.DoesNotExist:
            return None

    def get_user(self, user_id):
        try:
            tutor = Tutor.objects.get(pk=user_id)
            if tutor.is_active:
                return tutor
            return None
        except Tutor.DoesNotExist:
            return None
