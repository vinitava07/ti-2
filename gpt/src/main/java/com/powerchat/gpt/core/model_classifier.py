from io import BytesIO
import sys
import joblib


prompt = sys.argv[1]


file_path = 'gpt/src/main/java/com/powerchat/gpt/core/powerchat-gpt.joblib'

model = joblib.load(file_path)
print(model.predict([prompt])[0])
