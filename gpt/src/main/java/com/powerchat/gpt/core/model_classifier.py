from io import BytesIO
import sys
import joblib

prompt = sys.argv[1]

model = joblib.load('powerchat-gpt.joblib')
print(model.predict([prompt])[0])
