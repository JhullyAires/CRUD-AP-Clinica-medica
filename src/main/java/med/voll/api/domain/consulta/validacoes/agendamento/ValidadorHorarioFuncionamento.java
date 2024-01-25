package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoDeConsulta {
    public void validar (DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisEncerramentoClinica = dataConsulta.getHour() > 18;

        if (domingo || antesAberturaClinica || depoisEncerramentoClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }
    }
}
