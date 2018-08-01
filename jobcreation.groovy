import jenkins.model.Jenkins
import hudson.model.FreeStyleProject
import hudson.plugins.git.GitSCM
import hudson.tasks.Shell

def BRANCH_NAME = "disakau"
def job = Jenkins.instance.createProject(FreeStyleProject, "MNT-CD-module99-extcreated-job")
def gitScm = new GitSCM("https://github.com/DanielIsakau/build-principals.git")
gitScm.branches = [new hudson.plugins.git.BranchSpec("*/$BRANCH_NAME")]

job.scm = gitScm
job.getBuildersList().clear()
job.buildersList.add(new Shell('echo Hello'))
job.save()
