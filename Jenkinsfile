pipeline {
	agent any
	stages {
		stage ('BUILD DO BACKEND') {
			steps {
				bat 'mvn clean package -DskipTests=true'
			}
		}
		stage ('BUILD DO BACKEND') {
			steps {
				bat 'mvn test'
			}
		}
	}
}