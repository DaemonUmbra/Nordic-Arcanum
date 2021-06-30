package com.lordskittles.nordicarcanum.core;

import org.apache.maven.artifact.versioning.ArtifactVersion;

public class Version
{
    public int major;
    public int minor;
    public int build;

    public Version(ArtifactVersion version)
    {
        this(version.getMajorVersion(), version.getMinorVersion(), version.getBuildNumber());
    }

    public Version(int major, int minor, int build)
    {
        this.major = major;
        this.minor = minor;
        this.build = build;
    }

    @Override
    public int hashCode()
    {
        return this.major + this.minor + this.build;
    }

    @Override
    public String toString()
    {
        return this.major + "." + this.minor + "." + this.build;
    }
}
