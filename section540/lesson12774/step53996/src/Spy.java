/**
 * Spy – шпион, который логгирует о всей почтовой переписке,
 * которая проходит через его руки. Объект конструируется от
 * экземпляра Logger, с помощью которого шпион будет сообщать
 * о всех действиях. Он следит только за объектами класса
 * MailMessage и пишет в логгер следующие сообщения
 * (в выражениях нужно заменить части в фигурных скобках
 * на значения полей почты):
 *
 * 1) Если в качестве отправителя или получателя указан
 * "Austin Powers", то нужно написать в лог сообщение с
 * уровнем WARN: Detected target mail correspondence: from
 * {from} to {to} "{message}"
 *
 * 2) Иначе, необходимо написать в лог сообщение с
 * уровнем INFO: Usual correspondence: from {from} to {to}
 *
 */

import stepikClasses.MailMessage;
import stepikClasses.MailService;
import stepikClasses.Sendable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spy implements MailService {
    private final Logger logger;

    public Spy(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailMessage) {
            MailMessage mailMessage = (MailMessage) mail;
            if (isTargetMail(mailMessage)) {
                logger.log(
                        Level.WARNING,
                        "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                        new Object[] {mailMessage.getFrom(), mailMessage.getTo(), mailMessage.getMessage()});
            } else {
                logger.log(
                        Level.INFO,
                        "Usual correspondence: from {0} to {1}",
                        new Object[] {mailMessage.getFrom(), mailMessage.getTo()});
            }
        }
        return mail;
    }

    private boolean isTargetMail(MailMessage mailMessage) {
        return mailMessage.getFrom().equalsIgnoreCase(ConstNames.AUSTIN_POWERS) || mailMessage.getTo().equalsIgnoreCase(ConstNames.AUSTIN_POWERS);
    }
}