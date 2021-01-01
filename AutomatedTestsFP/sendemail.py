import base64
import mimetypes
import os
import os.path
import pickle
import sys
from email.mime.text import MIMEText
from email.mime.image import MIMEImage
from email.mime.audio import MIMEAudio
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
from googleapiclient import errors
from googleapiclient.discovery import build


#TODO
SITE = sys.argv[1]
ENV = sys.argv[2]
EMAIL_FROM = 'nodemailernodemand@gmail.com'
EMAIL_TO = 'danilaalbertthefirst@gmail.com;albe9220@stud.kea.dk'
EMAIL_SUBJECT = SITE + ' Selenium: Automation Test Results'
EMAIL_CONTENT = 'Results for test in ' + SITE + ' in ' + ENV
EMAIL_ATTACHMENT = r'C:\Users\danil\OneDrive\Desktop\FinalProject\automatedTests\AutomatedTestsFP\target\surefire-reports\emailable-report.html'

def get_test_result():
    f = open("C:/Users/danil/OneDrive/Desktop/FinalProject/automatedTests/AutomatedTestsFP/target/surefire-reports/emailable-report.html", "r")
    if 'class=\"failedeven\"' in f.read():
        return "Failed"
    else:
        return "Passed"

def get_service():
    """Gets an authorized Gmail API service instance.

    Returns:
        An authorized Gmail API service instance..
    """    

    # If modifying these scopes, delete the file token.pickle.
    SCOPES = [
        'https://www.googleapis.com/auth/gmail.readonly',
        'https://www.googleapis.com/auth/gmail.send',
    ]

    creds = None
    # The file token.pickle stores the user's access and refresh tokens, and is
    # created automatically when the authorization flow completes for the first
    # time.
    if os.path.exists('token.pickle'):
        with open('token.pickle', 'rb') as token:
            creds = pickle.load(token)
    # If there are no (valid) credentials available, let the user log in.
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(
                'C:/Users/danil/OneDrive/Desktop/FinalProject/automatedTests/AutomatedTestsFP/credentials.json', SCOPES)
            creds = flow.run_local_server(port=0)
        # Save the credentials for the next run
        with open('token.pickle', 'wb') as token:
            pickle.dump(creds, token)

    service = build('gmail', 'v1', credentials=creds)
    return service

def send_message(service, sender, message):
    """Send an email message.

    Args:
        service: Authorized Gmail API service instance.
        user_id: User's email address. The special value "me"
        can be used to indicate the authenticated user.
        message: Message to be sent.

    Returns:
        Sent Message.
    """
    try:
        sent_message = (service.users().messages().send(userId=sender, body=message)
                .execute())
        return sent_message
    except errors.HttpError as error:
        print('An error occurred: %s' % error)

def create_message_with_attachment(to, subject, message_text, test_result, file):
    """Create a message for an email.

    Args:
        sender: Email address of the sender.
        to: Email address of the receiver.
        subject: The subject of the email message.
        message_text: The text of the email message.
        file: The path to the file to be attached.

    Returns:
        An object containing a base64url encoded email object.
    """
    message = MIMEMultipart()
    message['to'] = to
    message['subject'] = subject

    msg = MIMEText("Test has " + test_result + "\n" + message_text)
    message.attach(msg)

    content_type, encoding = mimetypes.guess_type(file)

    if content_type is None or encoding is not None:
        content_type = 'application/octet-stream'
    main_type, sub_type = content_type.split('/', 1)
    if main_type == 'text':
        fp = open(file, 'r')
        msg = MIMEText(fp.read(), _subtype=sub_type)
        fp.close()
    elif main_type == 'image':
        fp = open(file, 'rb')
        msg = MIMEImage(fp.read(), _subtype=sub_type)
        fp.close()
    elif main_type == 'audio':
        fp = open(file, 'rb')
        msg = MIMEAudio(fp.read(), _subtype=sub_type)
        fp.close()
    else:
        fp = open(file, 'rb')
        msg = MIMEBase(main_type, sub_type)
        msg.set_payload(fp.read())
        fp.close()
    filename = os.path.basename(file)
    msg.add_header('Content-Disposition', 'attachment', filename=filename)
    message.attach(msg)
    raw = base64.urlsafe_b64encode(message.as_bytes())
    raw = raw.decode()
    return {'raw': raw}

if __name__ == '__main__':
    try:
        service = get_service()
        message = create_message_with_attachment(EMAIL_TO, EMAIL_SUBJECT, EMAIL_CONTENT, get_test_result(), EMAIL_ATTACHMENT)
        send_message(service, EMAIL_FROM, message)

        f = open('output.log', 'a')
        f.truncate(0) #Delete everything in the file
        f.write("Email with the test result has been sent succesfully")
        f.flush()
        print("Email with the test result has been sent succesfully")

    except Exception as e:
        print("error")
        raise