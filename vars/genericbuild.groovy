def call(Map config=[:]){
node {
    stage('SCM') {
    	echo 'Gathering code from version control'
	    git branch: '${branch}', url: 'https://github.com/peshlex/jenkins-test.git'
    }
    stage('Build') {
        try{
            echo 'Building....'
            // sh 'dotnet --version'
            // sh 'dotnet build ConsoleApp1'
            echo 'Building new feature'
            releasenotes(changes:"true")
        }catch(ex){
            echo 'Something went wrong'
            echo ex.toString();
            currentBuild.result = 'FAILURE'
        }
        finally{
            // cleanup
        }
    }
    stage('Test') {
        echo 'Testing....'
    }
    stage('Deploy') {
        echo 'Deploying....'
    }
}
}